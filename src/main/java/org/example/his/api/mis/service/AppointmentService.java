package org.example.his.api.mis.service;

import java.util.ArrayList;
import java.util.HashMap;

public interface AppointmentService {
    public ArrayList<HashMap> searchByOrderId(int orderId);
}

