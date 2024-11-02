package ait.cohort46.pulse.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Builder
@EqualsAndHashCode(of = "id")
public class PulseDto {
	private int id;
	private long timestamp;
	private int payload;
}
