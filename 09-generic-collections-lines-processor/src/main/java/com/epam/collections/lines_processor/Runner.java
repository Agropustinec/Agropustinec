package com.epam.collections.lines_processor;

import com.epam.collections.lines_processor.controller.LineController;
import com.epam.collections.lines_processor.model.LinesProcessor;
import com.epam.collections.lines_processor.view.LineView;

public class Runner {
    public static void main(String[] args) {
        LinesProcessor linesProcessor = new LinesProcessor();
        LineView lineView = new LineView();
        LineController lineController = new LineController(linesProcessor, lineView);
        lineController.run(args[0]);
    }
}
