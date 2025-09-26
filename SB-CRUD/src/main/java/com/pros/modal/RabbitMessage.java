package com.pros.modal;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RabbitMessage {
				private String sender;
				private String receiver;
				private String content;
				private LocalDateTime timestamp;
}
