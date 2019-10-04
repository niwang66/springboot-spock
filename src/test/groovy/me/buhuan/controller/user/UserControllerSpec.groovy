package me.buhuan.controller.user;

import me.buhuan.controller.BaseControllerSpec
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


/**
 * @author hbh
 * @version 1.0.0
 * @since 2017/12/10
 */
class UserControllerSpec extends BaseControllerSpec {

	void setup() {
		ResourceDatabasePopulator populate =
				new ResourceDatabasePopulator(context.getResources("classpath*:sql/user/*.sql"))
		DatabasePopulatorUtils.execute(populate, dataSource)
	}

	void cleanup() {

	}

	def "Test REST Find By User Id"() {
		given:
		def userId = 10001

		when:
		def result = get("/user/${userId}")


		then:
		result.andExpect(status().isOk())

		and:
		def res = result2Res(result.andReturn())

		then:
		res.name == 'hbh'
	}

	def "Test REST Save UserInfo"() {
		when:
		def result = post("/user", userInfo)

		then:
		result.andExpect(status().isOk())

		and:
		def res = result2Res(result.andReturn())

		then:
		res.id == id

		where:
		userInfo || id
		[id: 3, userId: 10003, name: "ls", sex: 1, address: "北京"] || 3
		[id: 4, userId: 10004, name: "ww", sex: 1, address: "广州"] || 4
	}

	def "Test Rest Delete UserInfo"() {

	}
}
