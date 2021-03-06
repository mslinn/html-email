# HTML EMail

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![GitHub version](https://badge.fury.io/gh/mslinn%2Fhtml-email.svg)](https://badge.fury.io/gh/mslinn%2Fhtml-email)

Generates HTML Email, with cc:, bcc:, an optional header image and an optional signature.
Uses [PureConfig](https://github.com/pureconfig/pureconfig) to parse default values of options provided in `reference.conf`,
or the values in a configuration file that you provide, such as `application.conf`.

Email addresses can be provided in the form `blah@gmail.com` or `Joe Blow <blah@gmail.com>`.

## Installation
Add this to your project's `build.sbt`:

    resolvers += "micronautics/scala on bintray" at "https://dl.bintray.com/micronautics/scala"

    libraryDependencies += "com.micronautics" %% "html-email" % "0.2.0" withSources()

## Scaladoc
[Here](http://mslinn.github.io/html-email/latest/api/com/micronautics/index.html)
