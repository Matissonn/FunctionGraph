package com.MatOs;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.print("Enter expression: ");
        Scanner sc = new Scanner(System.in);
        Graph.str = sc.nextLine();
        Graph.start();
    }
}