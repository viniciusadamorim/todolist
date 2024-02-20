package br.com.viniciusamori.todo.todolist.user;


import at.favre.lib.crypto.bcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserRepository iUserRepository;
    @PostMapping("/")
    public ResponseEntity create (@RequestBody UserModel userModel){
       var user = this.iUserRepository.findByUsername(userModel.getUsername());
       if (user != null ){
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já Cadastrado");
       }

       var passwordHash = BCrypt.withDefaults()
               .hashToString(12, userModel.getPassword().toCharArray());

       userModel.setPassword(passwordHash);


       var usedCreate = this.iUserRepository.save(userModel);
        return ResponseEntity.status(HttpStatus.OK).body(usedCreate);
    }
}
