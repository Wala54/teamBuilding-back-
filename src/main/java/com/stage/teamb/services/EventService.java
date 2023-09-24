package com.stage.teamb.services;

import com.stage.teamb.models.Event;
import com.stage.teamb.models.dtos.EventDTO;
import com.stage.teamb.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService implements GeneriqueService<EventDTO> {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    @Autowired
    public EventService(EventRepository eventRepository, EventMapper eventMapper) {
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
    }

    @Override
    public List<EventDTO> findAll() {
        List<Event> event = eventRepository.findAll();
        return eventMapper.toEventListDTO(event);
    }

    @Override
    public Optional<EventDTO> findOne(Long id) {
        Optional<Event> event = eventRepository.findById(id);
        return event.map(eventMapper::toEventDTO);
    }

    @Override
    public EventDTO saveOne(EventDTO eventDTO) {
        Event event = eventMapper.toEventEntity(eventDTO);
        Event savedEvent = eventRepository.save(event);
        return eventMapper.toEventDTO(savedEvent);
    }

    @Override
    public void deleteOne(Long id) {
        if (eventRepository.existsById(id)) {
            eventRepository.deleteById(id);
        } else {
            throw new RuntimeException("Event Not Exist");
        }
    }

    @Override
    public EventDTO update(EventDTO eventDTO) {
        Event event = eventMapper.toEventEntity(eventDTO);
        Event updatedEvent = eventRepository.save(event);
        return eventMapper.toEventDTO(updatedEvent);
    }




    public List<Event> findEvents(List<Long> eventIds) {
        return eventRepository.findAllById(eventIds);
    }

    public Event save(Event event) {
        return eventRepository.save(event);
    }
}