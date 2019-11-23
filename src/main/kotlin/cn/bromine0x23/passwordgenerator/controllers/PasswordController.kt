package cn.bromine0x23.passwordgenerator.controllers

import org.apache.commons.lang3.RandomStringUtils
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/password")
class PasswordController {

	companion object {
		private val CHARS: Array<String> = arrayOf(
			"ABCDEFGHIJKLMNOPQRSTUVWXYZ",
			"abcdefghijklmnopqrstuvwxyz",
			"0123456789",
			"!@#$%^&*"
		)
	}

	data class Options(
		val length: Int = 16,
		val uppercase: Boolean = true,
		val lowercase: Boolean = true,
		val digits: Boolean = true,
		val symbols: Boolean = false
	)

	@PostMapping
	fun create(
		@RequestBody options: Options
	): String {
		val chars = StringBuilder()
		if (options.uppercase) {
			chars.append(CHARS[0])
		}
		if (options.lowercase) {
			chars.append(CHARS[1])
		}
		if (options.digits) {
			chars.append(CHARS[2])
		}
		if (options.symbols) {
			chars.append(CHARS[3])
		}
		return RandomStringUtils.random(options.length, chars.toString())
	}
}

