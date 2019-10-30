import java.io.File
import java.nio.file._

object FileOps {
  def main(args: Array[String]): Unit = {
    wakeFile(new File("."))


  }

  def wakeFile(file: File): Unit = {
    implicit def makeFileVisitor(f: (Path) => Unit) = new SimpleFileVisitor[Path] {
      override def visitFile(p: Path, attrs: attribute.BasicFileAttributes) = {
        f(p)
        FileVisitResult.CONTINUE
      }
    }

    Files.walkFileTree(file.toPath, (f: Path) => println(f))
  }
  def subdirs (dir: File):Iterator[File] = {

    val children = dir.listFiles.filter(_.isDirectory)

    children.toIterator ++ children.toIterator.flatMap( subdirs _ )

  }
}
