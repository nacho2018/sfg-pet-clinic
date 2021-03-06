package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;


import java.util.Set;


@RunWith(MockitoJUnitRunner.class)
public class OwnerMapServiceTest {

    OwnerMapService ownerMapService;
    Owner owner;
    final Long ownerId = 1L;
    final String lastName = "Smith";

    @Before
    public void setUp() {

        //this is the initialization Spring would do for us
        ownerMapService = new OwnerMapService(new PetTypeMapService(), new PetMapService());
        owner = new Owner();
        owner.setId(ownerId);
        owner.setLastName(lastName);
        ownerMapService.save(owner);
        //ownerMapService.save(Owner.build().id(1L).build()); doesn't work
    }

    @Test
    public void findByLastName() {

        Owner smith = this.ownerMapService.findByLastName(lastName);

        Assert.assertNotNull(smith);
        Assert.assertEquals(this.ownerId, smith.getId());
    }

    @Test
    public void findByLastNameNotFound() {

        Owner smith = this.ownerMapService.findByLastName("foo");

        Assert.assertNull(smith);

    }

    @Test
    public void findAll() {
        Set<Owner> ownerSet = ownerMapService.findAll();
        Assert.assertEquals(1, ownerSet.size());
    }

    @Test
    public void findById() {
        Owner owner = ownerMapService.findById(ownerId);

        Assert.assertEquals(ownerId, owner.getId());
    }

    @Test
    public void saveExistingId() {
        final Long id = 2L;

        Owner owner2 = new Owner();
        owner2.setId(2L);

        Owner saveOwner = ownerMapService.save(owner2);

        Assert.assertEquals(id, saveOwner.getId());

    }

    @Test
    public void saveNoId() {
        Owner owner = new Owner();
        Owner savedOwner = ownerMapService.save(owner);

        Assert.assertNotNull(savedOwner);
        Assert.assertNotNull(savedOwner.getId());
    }

    @Test
    public void delete() {

        this.ownerMapService.delete(this.ownerMapService.findById(ownerId));

        Assert.assertEquals(0, this.ownerMapService.findAll().size());
    }

    @Test
    public void deleteById() {
        this.ownerMapService.deleteById(ownerId);

        Assert.assertEquals(0, this.ownerMapService.findAll().size());
    }


}