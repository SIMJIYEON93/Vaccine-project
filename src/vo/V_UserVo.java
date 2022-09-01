package vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class V_UserVo {
	private String u_id;
	private String u_birth;
	private String u_gender;
	private String p_num;
	private String u_nation;
}
