package vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class V_CenterVo {
	private int s_num;
	private String c_name;
	private String c_address;
	private String c_num;
	private String vaccine;
	private int v_count;
	

}
