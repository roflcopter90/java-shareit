package ru.practicum.shareit.booking.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.dto.BookingDtoCreate;
import ru.practicum.shareit.booking.service.BookingService;
import ru.practicum.shareit.utils.Create;

import java.util.List;

import static ru.practicum.shareit.item.controller.ItemController.SHARER_USER_ID;

@RestController
@RequestMapping("/bookings")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;

    @PostMapping
    public BookingDto save(@RequestHeader(SHARER_USER_ID) long userId,
                           @Validated({Create.class}) @RequestBody BookingDtoCreate bookingDtoCreate) {
        return bookingService.save(bookingDtoCreate, userId);
    }

    @PatchMapping("/{bookingId}")
    public BookingDto bookingApprove(@RequestHeader(SHARER_USER_ID) long ownerId,
                                     @PathVariable long bookingId,
                                     @RequestParam boolean approved) {
        return bookingService.bookingApprove(bookingId, ownerId, approved);
    }

    @GetMapping("/{bookingId}")
    public BookingDto findById(@RequestHeader(SHARER_USER_ID) long userId,
                               @PathVariable long bookingId) {
        return bookingService.findById(bookingId, userId);
    }

    @GetMapping
    public List<BookingDto> findByParam(@RequestHeader(SHARER_USER_ID) long userId,
                                        @RequestParam(required = false, defaultValue = "ALL") String state) {
        return bookingService.findAllByParam(userId, state);
    }

    @GetMapping("/owner")
    public List<BookingDto> findByOwner(@RequestHeader(SHARER_USER_ID) long userId,
                                        @RequestParam(required = false, defaultValue = "ALL") String state) {
        return bookingService.findAllByOwner(userId, state);
    }

}