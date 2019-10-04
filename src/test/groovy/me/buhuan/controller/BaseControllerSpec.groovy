package me.buhuan.controller

import groovy.json.JsonOutput
import groovy.json.JsonSlurper;
import me.buhuan.springbootspock.SpringbootSpockApplication
import org.junit.Before
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.context.WebApplicationContext
import spock.lang.Shared
import spock.lang.Specification

import javax.sql.DataSource



/**
 * @author hbh
 * @version 1.0.0
 * @since 2017/12/10
 */
@ContextConfiguration
@WebAppConfiguration
@SpringBootTest(classes = SpringbootSpockApplication.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@Transactional
class BaseControllerSpec extends Specification {

	@Shared
	protected MockMvc mockMvc

	@Autowired
	protected WebApplicationContext context

	@Autowired
	protected DataSource dataSource

	def jsonSlurper = new JsonSlurper()

	@Before
	def setupMockMvc() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build()
	}

	def get(String url) {
		return mockMvc.perform(MockMvcRequestBuilders.get(url))
	}

	def post(String url, data) {
		return mockMvc.perform(MockMvcRequestBuilders.post(url, JsonOutput.toJson(data)))
	}

	def put(String url, data) {
		return mockMvc.perform(MockMvcRequestBuilders.put(url, JsonOutput.toJson(data)))
	}

	def delete(String url) {
		return mockMvc.perform(MockMvcRequestBuilders.delete(url))
	}

	def result2Res(result) {
		def res = result.getResponse()
		res.setCharacterEncoding("UTF-8")
		String str = res.contentAsString
		return jsonSlurper.parseText(str)
	}
}
