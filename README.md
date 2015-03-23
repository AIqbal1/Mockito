# Mockito


Dummy objects are
analogous to null objects, but a dummy object is not used by the code under test.
Null objects (as in the pattern) are used in the code under test and are actively
interacted with, but they just produce zero behavior. If they weren't used, you'd just
use an actual null value.

Stubs help us to simulate these conditions. Stubs can also be programmed to return a
hardcoded result; for example, a stubbed bank account object can return the account
balance as $100.00.

A spy secretly obtains the information of a rival or someone very important. As the
name suggests, a spy object spies on a real object. A spy is a variation of a stub, but
instead of only setting the expectation, a spy records the method calls made to the
collaborator.

A mock object is a combination of a spy and a stub. It acts as an indirect output
for a code under test, such as a spy, and can also stub methods to return values or
throw exceptions, like a stub. A mock object fails a test if an expected method is not
invoked or if the parameters of the method don't match.

A fake object is a test double with real logic (unlike stubs) and is much more
simplified or cheaper in some way. Fake objects are working implementations. Mostly, the fake class extends the original
class, but it usually performs hacking, which makes it unsuitable for production.

examples:

The real object cannot be instantiated, such as when the constructor reads a
file, performs a JNDI lookup, and so on.

The real object has slow methods; for example, a class might have a
calculate () method that needs to be unit tested, but the calculate()
method calls a load ()method to retrieve data from the database. The
load() method needs a real database, and it takes time to retrieve data, so
we need to bypass the load() method to unit test the calculate behavior.
