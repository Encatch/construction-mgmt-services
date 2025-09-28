package com.example.jwtapp.service;

import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class DashboardService {

    /**
     * Returns mock dashboard data.
     * Replace this with real logic to fetch data from your database or APIs.
     */
    public Map<String, Object> getData() {
        Map<String, Object> dashboardData = new HashMap<>();

        dashboardData.put("totalProjects", 12);
        dashboardData.put("activeSites", 5);
        dashboardData.put("pendingTasks", 27);
        dashboardData.put("lastUpdated", new Date());

        return dashboardData;
    }
}
