package lila.coach

object UrlList {

  object youtube {

    val max = 6

    case class Url(value: String) extends AnyVal

    def apply(text: String): List[Url] =
      text.lines.toList.map(_.trim).filter(_.nonEmpty) flatMap toUrl take max

    private val UrlRegex = """.*(?:youtube\.com|youtu\.be)/(?:watch)?(?:\?v=)?([^"&?\/ ]{11}).*""".r

    /*
   * https://www.youtube.com/watch?v=wEwoyYp_iw8
   * https://www.youtube.com/embed/wEwoyYp_iw8
   */
    private def toUrl(line: String): Option[Url] = line match {
      case UrlRegex(id) => Url(s"https://www.youtube.com/embed/$id").some
      case _ => none
    }
  }

  object study {

    val max = 6

    case class StudyId(value: String) extends AnyVal

    private val UrlRegex = """.*(?:lichess\.org)/study/(\w+{8}).*$""".r

    def apply(text: String): List[StudyId] =
      text.lines.toList.map(_.trim).filter(_.nonEmpty) flatMap toId take max

    private def toId(line: String): Option[StudyId] = line match {
      case UrlRegex(id) => StudyId(id).some
      case _ => none
    }
  }
}
