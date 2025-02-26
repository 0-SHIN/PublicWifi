package dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class BookmarkGroupDto {
	private int id;
	private String name;
	private int orderNum;
	private String registDt;
	private String editDt;
}
