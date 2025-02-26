package dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class BookmarkDto {
	private int id;
	private String bookmarkGroupName;
	private String mainNm;
	private String registDt;
}
