package domain;

import java.io.Serializable;

import lombok.Data;

@Data
public class Customer implements Serializable {
	private static final long serialVersionUID = 3678107792576131001L;

	private String customerId;
	private String name;
	private String address;
	private Long noOfOrdersMade;

}
