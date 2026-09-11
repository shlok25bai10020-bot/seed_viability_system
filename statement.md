Submitted By: Shlok Mahesh Thakur
Registration Number: 25BAI10020
Course: Programming In Java

1. Problem Statement:  Seeds lose their ability to germinate naturally as time passes. When farmers or seed distributors store seeds for long periods, it is difficult to know if older batches will still grow properly when planted. Doing these calculations manually often results in distributing dead or weak seeds, which wastes money and ruins crop yields. So for this an automated system is required which tracks the seed age, viability and etc.

2. Scope of the Project: The scope of this project covers:
Managing seed inventory for four main crops: RICE, MAIZE, RAGI, and SOYBEAN.
Calculating how much seed viability drops over time based on harvest and expiry dates.
Checking if a seed batch meets the minimum percentage required for healthy planting.
Preventing invalid actions using clear error messages (e.g., trying to use expired seeds or deducting more stock than available).

3. Target Users:
Farmers
Buyers
Quality Checkers
Seed Stores Owners

4. High-Level Features:
Inventory Tracking: View all stored seeds along with crop type, quantity, and current status.
Automatic Viability Calculation: Uses the seed's age to calculate its current quality percentage.
Planting Suitability Check: Instantly shows whether a batch is APPROVED or BELOW_THRESHOLD for planting.
Stock Deduction with Safety Checks: Updates stock levels safely while rejecting requests that exceed available inventory.