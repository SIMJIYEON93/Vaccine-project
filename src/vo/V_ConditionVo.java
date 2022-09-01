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
public class V_ConditionVo {
	private String u_id;
	private int s_num;
	private String vaccine;
	private String inoc_f;
	private String inoc_s;
	private Date inoc_date;
}
