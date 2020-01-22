package converter;

import dto.RoleDto;
import entities.Role;

public class RoleConverter {

	public static RoleDto toRoleDto(Role role) {
		RoleDto roleDto = new RoleDto();
		roleDto.setId(role.getId());
		roleDto.setRolename(role.getRolename());
		roleDto.setDescription(role.getDescription());
		return roleDto;

	}

}
