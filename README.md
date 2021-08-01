
LAST UPDATED: 7/31/2021
# Book Browsing & Sorting:

## Purpose of feature
This feature allows the user to retrieve books from the database based on specific parameters.
The books can be sorted based on genre, copies sold, and ratings.


## Available Functions
booksWithXgenre(int x) - Retrieves all books from the database whose genre is 'x' where 'x' is an input of the user's choice.<br />
topKSellers(int k) - Retrieves the top 'k' books with the most copies sold where 'k' is an integer of the user's choice.<br />
booksOfXRatingAndHigher(int x) - Retrieves all of the books that have a rating of 'x' and higher where 'x' is an integer from 1 - 5 and 
it is an input of the user's choice.<br />
XBooksAtATime(int X, int start) - Retrieves 'x' books from the database where the indices of the rows fall within the interval <id>[start, start + x),
where 'x' is the input of the user's choice and 'start' is also an input of the user's choice.<br />

## GetMappings for each function
booksWithXgenre(int x) - /bookswithxgenre<br />
topKSellers(int k) - /topksellers<br />
booksOfXRatingAndHigher(int x) - /booksofxratingandhigher<br />
XBooksAtATime(int X, int start) - /xbooksatatime<br />


## How to use
In order to use this feature, you must follow the following template to make calls:<br />
"localhost8080:/<id>[DESIRED GET MAPPING]?[OPTIONAL_PARAMETER_1]&[OPTIONAL PARAMETER 2]"<br />
For example, if you wanted to retrieve all of the books with the genre "mystery", you would enter the following:<br />
localhost8080:/bookswithxgenre?x=mystery<br />
This will retrieve all of the books with that exact genre.

## Examples
Goal: Retrieve books with "horror" genre<br />
GET Call: localhost8080:/bookswithxgenre?x=horror

Goal: Retrieve the top 10 sellers<br />
GET Call: localhost8080:/topksellers?k=10

Goal: Retrieve books with a rating of 3 and higher<br />
GET Call: localhost8080:/booksofxratingandhigher?x=3

Goal: Retrieve 5 books starting at row 3 in the database (retrieve books from the range [3, 8)<br />
GET Call: localhost8080:/xbooksatatime?x=5&start=3
