package com.rohit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rohit.models.Entries;

public interface EntriesRepo extends JpaRepository<Entries, Integer> {

	public Entries findByApi(String api);
}
