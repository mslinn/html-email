package com.micronautics

import org.apache.commons.mail.HtmlEmail

case class EMailConfig(smtp: Smtp)

object Smtp {
  def apply: EMailConfig = pureconfig.loadConfigOrThrow[EMailConfig]
}

case class Smtp(
  smtpFrom: String,
  smtpHostName: String,
  smtpPort: Int,
  smtpUser: String,
  smtpPassword: String,
  smtpSsl: Boolean = false,
  smtpTls: Boolean = true,
  maybeEmailLogoUrl: Option[String] = None, // URL
  maybeSignature: Option[String] = None
) {
  import scala.util.Try

  def send(mailTo: String, mailCc: List[String], mailBcc: List[String], subjectLine: String, mailBody: String): Try[Boolean] = {
    val email: HtmlEmail = prepare(mailTo, mailCc, mailBcc, subjectLine, mailBody)

    Try {
      email.send()
      true
    }
  }

  def prepare(mailTo: String, mailCc: List[String], mailBcc: List[String], subjectLine: String, mailBody: String): HtmlEmail = {
    val email = new HtmlEmail
    email.setCharset("UTF-8")
    email.setSubject(subjectLine)
    email.setFrom(smtpFrom)
    email.addTo(mailTo)
    mailCc foreach { cc => email.addCc(cc) }
    mailBcc foreach { bcc => email.addBcc(bcc) }

    val cid = (for {
      logoStr <- maybeEmailLogoUrl
    } yield {
      val cid = email.embed(new java.net.URL(logoStr), "Logo")
      s"""<img src=cid:$cid><br clear="all" style="margin-bottom: 1em;"/>"""
    }).getOrElse("")

    val htmlText = s"""<html>
                      |<body>
                      | $cid
                      | $mailBody
                      | ${ maybeSignature.map{ x => s"<p style='font-size: 18pt; margin-top: 18pt;'>$x</p>" }.mkString }
                      |</body>
                      |</html>
                      |""".stripMargin
    email.setHtmlMsg(htmlText)
    email.setHostName(smtpHostName)
    email.setTextMsg("Your email client does not support HTML messages")
    email.setStartTLSEnabled(smtpTls)
    email.setSSLOnConnect(smtpSsl)

    if (smtpUser.nonEmpty)
      email.setAuthentication(smtpUser, smtpPassword)

    email
  }
}
