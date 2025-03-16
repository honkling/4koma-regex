package cc.ekblad.toml.parser

import cc.ekblad.konbini.parser
import kotlin.text.Regex

private const val pattern = "/(?<pattern>(?:\\\\/|[^/])*)/(?<flags>[mixs]*)"
private val matcher = Regex(pattern)
private val flagToOption = mapOf(
    'm' to RegexOption.MULTILINE,
    'i' to RegexOption.IGNORE_CASE,
    'x' to RegexOption.COMMENTS,
    's' to RegexOption.DOT_MATCHES_ALL
)

internal val regex = parser {
    val result = matcher.matchAt(input, position)
        ?: fail("Expected pattern '$pattern', but there was no match.")

    position += result.value.length
    val (_, thePattern, flags) = result.groupValues
    println("Flags: ${flags.toList()}")
    val options = flags.map { flagToOption[it]!! }.toSet()
    Regex(thePattern, options)
}