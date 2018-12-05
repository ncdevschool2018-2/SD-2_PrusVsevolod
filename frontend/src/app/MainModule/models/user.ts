import {Role} from "./role";

export class User {
  id: string;
  login: string;
  email: string;
  password: string;
  role: Role = new Role();

  static cloneUser(user: User): User{
      let clonedUser: User = new User();
      clonedUser.id = user.id;
      clonedUser.login = user.login;
      clonedUser.email = user.email;
      clonedUser.password = user.password;
      clonedUser.role = Role.cloneRole(user.role);
      return clonedUser;
  }
}
