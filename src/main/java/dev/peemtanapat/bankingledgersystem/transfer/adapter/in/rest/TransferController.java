package dev.peemtanapat.bankingledgersystem.transfer.adapter.in.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.peemtanapat.bankingledgersystem.transfer.adapter.dto.TransferHttpRequest;
import dev.peemtanapat.bankingledgersystem.transfer.adapter.dto.TransferHttpResponse;
import dev.peemtanapat.bankingledgersystem.transfer.adapter.in.rest.mapper.TransferControllerMapper;
import dev.peemtanapat.bankingledgersystem.transfer.core.application.dto.TransferDto;
import dev.peemtanapat.bankingledgersystem.transfer.core.application.port.in.TransferUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/transfer")
public class TransferController {

    private final TransferUseCase transferUseCase;
    private final TransferControllerMapper mapper;

    @PostMapping
    public TransferHttpResponse transfer(@RequestBody @Valid TransferHttpRequest request) {
        TransferDto transferDto = transferUseCase.transfer(mapper.requestToCommand(request));

        return mapper.dtoToResponse(transferDto);
    }

}
