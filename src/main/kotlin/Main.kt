import java.io.File
import kotlin.io.path.Path
import kotlin.io.path.createDirectory
import kotlin.io.path.isDirectory
import kotlin.io.path.listDirectoryEntries

fun encrypt(msg: String, key: String): String {
    val chars: List<Char> = msg.toList()
    val keyChars: List<Char> = key.toList()

    val keyXor = IntArray(keyChars.size)

    for(i in 0 until keyChars.size) keyXor.set(i, keyChars.get(i).code)

    var response = ""

    for(i in 0 until chars.size) {
        val charCode = chars.get(i).code
        response += charCode.xor(keyXor.get(i)).toChar()
    }

    return response

}

private val chars = ('a'..'z') + ('A'..'Z') + ('0'..'9')
private fun getRandomHash(length: Int): String = List(length) { chars.random() }.joinToString("")

fun main() {
    if(!Path("./output").isDirectory()) Path("./output").createDirectory()

    println("1. Encrypt new value\n2. Decrypt file")
    var choice = readLine()?.toIntOrNull()

    while(choice == null) {
        println("1. Encrypt new value\n2. Decrypt file")
        choice = readLine()?.toIntOrNull()
    }

    when(choice) {
        1 -> encryptFile()
        2 -> decryptFile()
    }


}

fun encryptFile() {
    println("\nEnter value to encrypt")
    val value = readLine().toString()
    val password = getRandomHash(value.length)

    val result = encrypt(value, password)
    val file = File("./output/${getRandomHash(16)}.txt")
    file.writeText(result)

    println("Generated file with password: $password")
}

fun decryptFile() {
    val files = Path("./output").listDirectoryEntries()

    if(files.size < 1) {
        println("* Output folder is empty!")
        return
    }

    println("\nPick a file (1-${files.size})")
    for(i in 0..files.size-1) {
        val file = files.get(i)
        println("${i+1}. ${file.toString().replace(".\\output\\", "")}")
    }

    var fileId = readLine()?.toIntOrNull()

    while(fileId == null || fileId > files.size || fileId < 1) {
        println("* Pick a valid id (1-${files.size})")
        fileId = readLine()?.toIntOrNull()
    }

    val file = files.get(fileId-1).toFile()

    println("Enter encryption password")
    val password = readLine().toString()
    val encrypted = file.readText()

    // reverses the encryption
    println(encrypt(encrypted, password))
}