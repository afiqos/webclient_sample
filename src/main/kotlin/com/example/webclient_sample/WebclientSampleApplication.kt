package com.example.webclient_sample

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.time.LocalDate

@SpringBootApplication
class WebclientSampleApplication

fun main(args: Array<String>) {
	runApplication<WebclientSampleApplication>(*args)

	val fileName = "my_excel_file"
	val fileDate = LocalDate.parse("2020-07-01")
	val message = "Missing ${fileName}_${fileDate}"

	val api = WebClientAPI()

//	api.getEmployeeById().subscribe()
//	api.addNewCustomer().subscribe()
	api.sendEmail(message).subscribe()
}
