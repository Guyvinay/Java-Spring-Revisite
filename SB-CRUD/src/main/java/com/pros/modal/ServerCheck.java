package com.pros.modal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ServerCheck {

				public Long startUTC;
				public Long startServerUTC;
				public Long endServerUTC;
				public Long endUTC;

}
