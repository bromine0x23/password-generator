package cn.bromine0x23.passwordgenerator

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PasswordGeneratorApplication

fun main(args: Array<String>) {
	runApplication<PasswordGeneratorApplication>(*args)
}
