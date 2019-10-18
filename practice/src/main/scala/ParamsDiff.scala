import java.io.{BufferedWriter, File, FileWriter, PrintWriter}

import scala.io.Source
import scala.util.matching.Regex

/**
  * 找出4.2没写的变量
  */
object ParamsDiff {
  def main(args: Array[String]): Unit = {
    val str41 = Source.fromFile("D:\\work\\git\\xml-translator\\4.1\\params.xslt","utf-8").mkString
    val str42 = Source.fromFile("D:\\work\\git\\xml-translator\\4.2\\params.xslt","utf-8").mkString
    //    println(str)
    val pattern = "(?<=<xsl:variable name=\")\\w+".r
    val set41 = (pattern findAllIn str41).toSet
    val set42 = (pattern findAllIn str42).toSet
    val s = set41 diff set42
    println(s)
    println( s size )

    s.foreach(name => {
      val pattern2 = new Regex(s"""(?s)<xsl:variable\\s+name=\"$name\">.*?</xsl:variable>""")
      val result = pattern2 findAllIn str41
      //这里打印了，就不 输出不了了
//      println(result.mkString)
      writeFile("hello.txt",result.mkString)
    })

  }
  def writeFile(filename: String, s: String): Unit = {
    val file = new File(filename)
    val bw = new BufferedWriter(new FileWriter(file))
    bw.write(s)
    bw.close()
  }
}
