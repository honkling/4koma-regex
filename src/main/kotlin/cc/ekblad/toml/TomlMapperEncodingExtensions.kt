package cc.ekblad.toml

import cc.ekblad.toml.model.TomlDocument
import cc.ekblad.toml.model.TomlException
import cc.ekblad.toml.serialization.write
import java.io.OutputStream
import java.nio.file.Path

/**
 * Encodes the given [value] into a valid TOML document.
 * If [value] does not serialize to a valid TOML document (i.e. a map of zero or more keys),
 * an [TomlException.SerializationError] is thrown.
 */
fun TomlMapper.encodeToDocument(value: Any): TomlDocument = encode(value) as? TomlDocument
    ?: throw TomlException.SerializationError(
        "class '${value.javaClass.name}' doesn't serialize to a valid TOML document",
        null
    )

/**
 * Encodes the given [value] into a valid TOML document and serializes it into a string.
 * If [value] does not serialize to a valid TOML document (i.e. a map of zero or more keys),
 * an [TomlException.SerializationError] is thrown.
 */
fun TomlMapper.encodeToString(value: Any): String {
    val stringBuffer = StringBuffer()
    encodeToDocument(value).write(stringBuffer)
    return stringBuffer.toString()
}

/**
 * Serializes given [value] into a valid TOML document and writes it to the given [Appendable].
 * If [value] does not serialize to a valid TOML document (i.e. a map of zero or more keys),
 * an [TomlException.SerializationError] is thrown.
 */
fun TomlMapper.encodeTo(output: Appendable, value: Any) {
    encodeToDocument(value).write(output)
}

/**
 * Serializes given [value] into a valid TOML document and writes it to the given [OutputStream].
 * If [value] does not serialize to a valid TOML document (i.e. a map of zero or more keys),
 * an [TomlException.SerializationError] is thrown.
 */
fun TomlMapper.encodeTo(outputStream: OutputStream, value: Any) {
    encodeToDocument(value).write(outputStream)
}

/**
 * Serializes given [value] into a valid TOML document and writes it to the file indicated by the given [Path].
 * If [value] does not serialize to a valid TOML document (i.e. a map of zero or more keys),
 * an [TomlException.SerializationError] is thrown.
 */
fun TomlMapper.encodeTo(path: Path, value: Any) {
    encodeToDocument(value).write(path)
}
