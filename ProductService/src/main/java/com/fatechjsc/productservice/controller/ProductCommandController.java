package com.fatechjsc.productservice.controller;

import com.fatechjsc.productservice.command.command.product.CreateProductCommand;
import com.fatechjsc.productservice.dto.ProductDto;
import com.fatechjsc.productservice.exceptions.InvalidParamException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/product")
public class ProductCommandController {
    private final CommandGateway commandGateway;

    @PostMapping("add")
    public String add(@Valid @RequestBody ProductDto model) throws Exception{
        CreateProductCommand command = new CreateProductCommand();
        BeanUtils.copyProperties(model, command);
        command.setProductId(UUID.randomUUID().toString());
        commandGateway.sendAndWait(command);
        return "Create Product success";
    }
}
