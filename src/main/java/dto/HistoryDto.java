package dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class HistoryDto {
	private int id;
	private String lnt;
	private String lat;
	private String viewDt;
}
