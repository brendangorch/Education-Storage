# Education-Storage
A JavaFX-based database application created using ApacheDerby. The application stores grades across 4 years of an undergraduate program and includes a GPA calculator that is highly customizable. The GPA calculator displays 
yearly GPAs, term GPAs, highest/lowest grades, and total credits. The user can also choose to calculate their GPA based on specific years.
<br />
<br />
<br />

## Adding New Grades Demo
The user is displayed 5 separate tabs (one for each year and the GPA calculator) and a list of instructions upon opening the application:
<br />
<br />
![edustoragemain](https://github.com/brendangorch/Education-Storage/assets/145873615/5651a0a5-5503-412a-ae24-de4f72fd31e2)
<br />
<br />
Adding grades to a year is simple. You just click the tab of the year you want to add grades to, right click the term and press add, enter the course name, grade, and weight, and click save. The grades will then be added to the database and shown on the left side. You
can also edit or delete courses/grades by right clicking the course and clicking edit/delete. Below is a sample of the UI:
<br />
<br />
![yearOne](https://github.com/brendangorch/Education-Storage/assets/145873615/5125fd66-741f-43b6-9f37-af8028594a4f)
<br />
<br />
The GPA calculator displays a lot of information without requiring any user input. It will pull grades from all years from the database (or show TBD if no grades exist for the year). Pressing the light blue buttons will switch the
format of the grades between percent, letter, and 4.0 scale. At the bottom of this tab, the user can select years to calculate cumulative GPAs:
<br />
<br />
![gpacalculator](https://github.com/brendangorch/Education-Storage/assets/145873615/d1ed2cbc-44db-4af8-8799-e4be8049ebe3)
