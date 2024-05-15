package com.riwi.beautySalon.infraestructure.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.riwi.beautySalon.api.dto.request.ClientRegisterReq;
import com.riwi.beautySalon.api.dto.request.EmployeeRegisterReq;
import com.riwi.beautySalon.api.dto.request.LoginReq;
import com.riwi.beautySalon.api.dto.request.RegisterReq;
import com.riwi.beautySalon.api.dto.response.AuthResp;
import com.riwi.beautySalon.domain.entities.ClientEntity;
import com.riwi.beautySalon.domain.entities.Employee;
import com.riwi.beautySalon.domain.entities.User;
import com.riwi.beautySalon.domain.repositories.ClientRepository;
import com.riwi.beautySalon.domain.repositories.EmployeeRepository;
import com.riwi.beautySalon.domain.repositories.UserRepository;
import com.riwi.beautySalon.infraestructure.abstract_services.IAuthService;
import com.riwi.beautySalon.infraestructure.helpers.JwtService;
import com.riwi.beautySalon.utils.enums.Role;
import com.riwi.beautySalon.utils.exception.BadRequestException;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Transactional
@Service
@AllArgsConstructor
public class AuthService implements IAuthService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final JwtService jwtService;

    @Autowired
    private final PasswordEncoder encoder;

    @Autowired
    private final AuthenticationManager authenticationManager;

    @Autowired
    private final ClientRepository clientRepository;
    
    @Autowired
    private final EmployeeRepository employeeRepository;

    @Override
    public AuthResp login(LoginReq request) {
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    request.getUserName(),
                    request.getPassword())); 
        } catch (Exception e) {
            throw new BadRequestException("Invalid credentials");
        }
        
        User user = this.findByUserName(request.getUserName());

        if (user == null) {
            throw new BadRequestException("Invalid User");
        }

        return AuthResp
                .builder()
                .message("All correct")
                .token(this.jwtService.getToken(user))
                .build();
    }

    @Override
    public AuthResp register(RegisterReq request) {
       /*1. Validar que userName no exista */
       User exist =this.findByUserName(request.getUserName());

       if (exist != null) {
            throw  new BadRequestException("Este nombre de usuario ya está registrado.");
       }

       /*2. Construimos el nuevo usuario */

       User user = User.builder()
                    .userName(request.getUserName())
                    //Guardar la contraseña codificada
                    .password(encoder.encode(request.getPassword()))
                    .role(Role.ADMIN)
                    .build();
        
        /*3. Guardar el nuevo usuario en la db*/
       this.userRepository.save(user);

       return AuthResp.builder()
                .message("Se registró exitosamente")
                .token(this.jwtService.getToken(user))
                .build();

    }

    @Override
    public AuthResp registerClient(ClientRegisterReq registerReq){
        User exist = this.findByUserName(registerReq.getUserName());

        if (exist != null) {
            throw new BadRequestException("User already register");
        }

        User user = User.builder()
                        .userName(registerReq.getUserName())
                        .password(encoder.encode(registerReq.getPassword()))
                        .role(Role.CLIENT)
                        .build();

        User userSave = this.userRepository.save(user);

        ClientEntity client = ClientEntity.builder()
                                        .firstName(registerReq.getFirstName())
                                        .lastName(registerReq.getLastName())
                                        .phone(registerReq.getPhone())
                                        .email(registerReq.getEmail())
                                        .user(userSave)
                                        .appointments(new ArrayList<>())
                                        .build();

        this.clientRepository.save(client);

        return AuthResp.builder()
                    .message("Client register correctlly")
                    .token(this.jwtService.getToken(userSave))
                    .build();
    }

    @Override
    public AuthResp registerEmployee(EmployeeRegisterReq request){
        User exist = this.findByUserName(request.getUserName());

        if (exist != null) {
            throw new BadRequestException("User already register");
        }

        User user = User.builder()
                        .userName(request.getUserName())
                        .password(encoder.encode(request.getPassword()))
                        .role(Role.EMPLOYEE)
                        .build();

        User userSave = this.userRepository.save(user);

        Employee employee = Employee.builder()
                                        .firstName(request.getFirstName())
                                        .lastName(request.getLastName())
                                        .email(request.getEmail())
                                        .role(request.getRole())
                                        .phone(request.getPhone())
                                        .user(userSave)
                                        .build();

        this.employeeRepository.save(employee);

        return AuthResp.builder()
                    .message("Employee register correctlly")
                    .token(this.jwtService.getToken(userSave))
                    .build();
    }

    private User findByUserName(String userName){
        return this.userRepository.findByUserName(userName)
                    .orElse(null);
    }
}
