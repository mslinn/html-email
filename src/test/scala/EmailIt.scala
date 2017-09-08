import com.micronautics.{EMailConfig, Smtp}

object EmailIt extends App {
  val subjectLine = s"Test email"
  val emailLogoUrl = "http://siteassets.scalacourses.com/images/ScalaCoursesHeadingLogo371x56.png"
  val smtp = Smtp.apply.smtp.copy(
    maybeEmailLogoUrl = Some(emailLogoUrl),
    maybeSignature = Some("<i><b>ScalaCourses</b><br/>World-class online training</i>")
  )
  val emailConfig: EMailConfig = EMailConfig(smtp)
  val mailBody: String = s"""Dear Santa,
                            |
                            |<p>I am thinking of a greedy holiday season.</p>
                            |
                            |<p>Best regards,</p>
                            |
                            |<p>Silly Person</p>
                            |""".stripMargin

  emailConfig.smtp.send(
    mailTo = "mslinn@micronauticsresearch.com",
    mailCc = List("mslinn@gmail.com"),
    mailBcc = List("mslinn@mslinn.com"),
    subjectLine,
    mailBody
  )
  emailConfig.smtp.send(
    mailTo = "Mike Slinn <mslinn@micronauticsresearch.com>",
    mailCc = List("Joe Blow <mslinn@gmail.com>"),
    mailBcc = List("Jane Doe <mslinn@mslinn.com>"),
    subjectLine,
    mailBody
  )
}