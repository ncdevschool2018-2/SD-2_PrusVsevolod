export class Role {
  id: string;
  name: string;

  static cloneRole(role: Role): Role {
    let clonedRole: Role = new Role();
    clonedRole.id = role.id;
    clonedRole.name = role.name;
    return clonedRole;
  }
}
