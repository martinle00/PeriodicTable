package com.example.periodictable.asynctask;

import com.example.periodictable.Element;

import java.util.List;

public interface AsyncTaskGetDelegate {
    void handleTaskGetResult(List<Element> elements);
}
