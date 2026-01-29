package com.tw.joi.delivery.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** Base class for delivery outlets. */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Outlet {

  protected String name;

  protected String description;

  protected String outletId;
}
