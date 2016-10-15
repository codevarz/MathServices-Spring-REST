## Contributing

Here's a quick and dirty description of how to contribute to the Math Service!

### Code Style
In terms of code style, we use the
[Google Java Style Guide](https://google.github.io/styleguide/javaguide.html). Please keep your
submissions consistent at least roughly consistent with the style described therein.

Consistency is important to us within the layout of the application as well. When adding new
services, please give them the same look and feel externally as existing services. As well, please
take care that your implementation is consistent with the implementation of neighboring services.
We try to follow the "principle of least surprise", so that our clients and our maintainers alike
can easily work with our service.

### Naming Conventions
Our goal is to use the appropriate names for the math constructs that we expose. As an example, we
use the terms _augend_ and _addend_ to describe the components being added in the `/addition`
service, even though those terms aren't in common use, since these are the mathematically proper
names for these arguments.
 
When adding new services or arguments, please take care to be consistent and use proper terminology.
A quick Google search will likely reveal the proper terms. For example the first result for the
query _terms used for addition_ is [here](http://www.factmonster.com/ipka/A0881931.html).

### Backwards Compatibility
Changes to the service should remain backwards compatible with existing clients (well, besides
fixing obviously debilitating bugs). Additive changes to documents are acceptable, as we require
clients to ignore fields that they do not understand.

### Test Coverage
For any code changes, there likely need to be test cases as well. If you are adding a new feature,
then that feature should get new test coverage. If you are fixing a bug, then test coverage relevant
to that bug should be added to prevent future regressions.

### Logging
Application-level tracing information should be logged at level 'INFO' or below. Please do not
pollute the WARN/ERROR logs with mundane/routine information.
