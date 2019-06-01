package br.com.trf.reactive

import io.kotlintest.extensions.TestListener
import io.kotlintest.matchers.string.shouldContain
import io.kotlintest.specs.StringSpec
import io.kotlintest.spring.SpringListener
import org.junit.platform.commons.logging.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import reactor.test.StepVerifier
import java.time.Duration

@SpringBootTest
class JokerServiceTest : StringSpec() {

	private val logger = LoggerFactory.getLogger(JokerServiceTest::class.java)

	@Autowired
	private lateinit var jokerService: JokerService

	override fun listeners(): List<TestListener> {
		return listOf(SpringListener)
	}

	init {
	    "should get a joke" {
			val joke = jokerService.getJoke("Thiago", "Faria")
			logger.info { joke }
			joke shouldContain "Thiago|Faria".toRegex()
		}


	    "should get a joke async blocking request" {
			val joke = jokerService.getJokeAsync("Thiago", "Faria").block(Duration.ofSeconds(2L))
			logger.info { joke }
			joke shouldContain "Thiago|Faria".toRegex()
		}

		"should get a joke async" {
			val joke = jokerService.getJokeAsync("Thiago", "Faria")

			StepVerifier.create(joke).assertNext {
				logger.info { it }
				it shouldContain "Thiago|Faria".toRegex()
			}.verifyComplete()
		}

	}

}
