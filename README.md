Seed Viability & Inventory Management System
A simple Java project designed to help seed stores and farmers manage their seed stock. It keeps track of stored seeds, calculates whether older seeds are still good enough to plant, and prevents anyone from selling expired or out-of-stock seeds.

What Does This Project Do?
1. Tracks 4 Common Crops: Manages seed batches for Rice, Maize, Ragi, and Soybean.

2. Checks Seed Quality: Seeds lose their ability to grow as they get older. This program calculates their current quality (viability) based on how many months have passed since harvest.

3. Warns When Seeds Expire: Automatically flags old batches as expired so bad seeds aren't planted.

4. Updates Stock: Lets you deduct seed quantities when stock is used or sold, and stops you if you try to take out more than you have.

5. Generates Simple Reports: Shows a clear list of all batches with their remaining quantity and whether they are safe to plant.

Technologies Used:
1. Java : The core programming language used to build the app.
2. Git & GitHub: Used for source code management and project submission.

seed_viability_system/
├── src/
│   ├── exception/         # Handles errors (like expired seeds or low stock)
│   ├── model/             # Stores the seed data and crop types
│   ├── service/           # Logic for calculations, inventory, and reports
│   └── Main.java          # The interactive menu that runs the app
├── README.md              # Project instructions and overview
└── statement.md           # Project problem statement and scope