package com.sport.sport.entities;

import java.math.BigDecimal;
import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Seats", schema = "public")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name") private String name;
    @Column(name = "total_number") private Long total_number;
    @Column(name = "available_number") private Long available_number;
    @Column(name = "price") private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event event;

    public Long getId() { return id; }
    public String getName() { return name; }
    public Long getTotalNumber() { return total_number; }
    public Long getAvailableNumber() { return available_number; }
    public BigDecimal getPrice() { return price; }
    public Event getEvent() { return event; }

    public void setEvent(Event event) { this.event = event; }

    public Seat() {}
    public Seat(Long id, String name, Long total_number, Long available_number, BigDecimal price)
    {
        this.id = id;
        this.name = name;
        this.available_number = available_number;
        this.total_number = total_number;
        this.price = price;
    }
    public Seat(String name, Long total_number, Long available_number, BigDecimal price)
    {
        this.name = name;
        this.available_number = available_number;
        this.total_number = total_number;
        this.price = price;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, available_number, total_number, price, event);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Seat s = (Seat) o;
        return id == s.id && name.equals(s.name) && available_number == s.available_number &&
                total_number == s.total_number && price.equals(s.price) && event.equals(s.event);
    }
}
