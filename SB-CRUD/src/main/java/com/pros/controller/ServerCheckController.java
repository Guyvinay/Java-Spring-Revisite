package com.pros.controller;


import com.pros.modal.ServerCheck;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Random;

@RestController
@RequestMapping(value = "/server/health/check/rtt")
@CrossOrigin("*")
public class ServerCheckController {

				@PostMapping
				public ResponseEntity<ServerCheck> getServerCheck(@RequestBody ServerCheck serverCheck) throws InterruptedException {

								return new ResponseEntity<>(getServerResponse(serverCheck), HttpStatus.OK);
				}

				private ServerCheck getServerResponse(ServerCheck ServerCheck) throws InterruptedException {
								ServerCheck.setStartServerUTC(Instant.now().toEpochMilli());
								Thread.sleep(100 + new Random().nextInt(101));
								// TimeUnit.SECONDS.sleep(100 + new Random().nextInt(101));
								// TimeUnit.MILLISECONDS.sleep(100 + new Random().nextInt(101));
								// TimeUnit.HOURS.sleep(1);
								ServerCheck.setEndServerUTC(Instant.now().toEpochMilli());
								return ServerCheck;
				}
}
