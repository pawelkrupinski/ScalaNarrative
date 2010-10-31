package com.youdevise.test.narrative;

import Types._

trait Actor[T] {
    def perform(action: Action[T]);

    def grabUsing[D](extractor: Extractor[D, T]): D;
}

