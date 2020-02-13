<?php

namespace CantineBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * plat
 *
 * @ORM\Table(name="plat")
 * @ORM\Entity(repositoryClass="CantineBundle\Repository\platRepository")
 */
class plat
{
    public function __toString()
    {
        return $this->nomplat;
    }
    /**
     * @var int
     *
     * @ORM\Column(name="id", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    private $id;

    /**
     * @var string
     *
     * @ORM\Column(name="nomplat", type="string", length=255)
     */
    private $nomplat;


    /**
     * Get id
     *
     * @return int
     */
    public function getId()
    {
        return $this->id;
    }

    /**
     * Set nomplat
     *
     * @param string $nomplat
     *
     * @return plat
     */
    public function setNomplat($nomplat)
    {
        $this->nomplat = $nomplat;

        return $this;
    }

    /**
     * Get nomplat
     *
     * @return string
     */
    public function getNomplat()
    {
        return $this->nomplat;
    }
}

