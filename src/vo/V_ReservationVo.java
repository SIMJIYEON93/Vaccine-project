package vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class V_ReservationVo {
	private String u_id;
	private int s_num;
	private String vaccine;
	private Date inoc_date;
	private String u_nation;

}
