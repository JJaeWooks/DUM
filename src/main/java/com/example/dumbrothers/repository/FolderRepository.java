package com.example.dumbrothers.repository;
import com.example.dumbrothers.entity.Folder;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface FolderRepository extends CrudRepository<Folder,Long> {
    @Override
    ArrayList<Folder> findAll();
}
