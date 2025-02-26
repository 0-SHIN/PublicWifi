package dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class WifiDto {
	private String mgrNo;
	private String wrdofc;
	private String mainNm;
	private String adres1;
	private String adres2;
	private String instlFloor;
	private String instlTy;
	private String instlMby;
	private String svcSe;
	private String cmcwr;
	private String cnstcYear;
	private String inoutDoor;
	private String remars3;
	private String lat;
	private String lnt;
	private String workDttm;
	private double distance;
}
