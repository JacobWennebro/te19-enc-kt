import java.io.*
import java.util.*

fun numberGenerator(iterations: Int): IntArray {
    val r = Random()
    val numbers = IntArray(iterations)
    for(i in 0 until iterations) numbers.set(i, r.nextInt())
    return numbers
}

fun encrypt(msg: String, key: String): String {
    val chars: List<Char> = msg.toList();
    val keyChars: List<Char> = key.toList();

    val keyXor = IntArray(keyChars.size);

    for(i in 0 until keyChars.size) keyXor.set(i, keyChars.get(i).toInt());

    var response = "";

    for(i in 0 until chars.size) {
        val charCode = chars.get(i).toInt();
        response += charCode.xor(keyXor.get(i)).toChar();
    }

    return response;
}

fun decrypt(hash: String, key: String): String {
    return encrypt(hash, key);
}


fun main(args: Array<String>) {

    val res = encrypt("leeråy", "boopip");

    println(decrypt(res, "boopip"));
}