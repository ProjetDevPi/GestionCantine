<?php

namespace CantineBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * menu
 *
 * @ORM\Table(name="menu")
 * @ORM\Entity(repositoryClass="CantineBundle\Repository\menuRepository")
 */
class menu
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
     * @ORM\Column(name="boisson", type="string", length=255)
     */
    private $boisson;

    /**
     * @var string
     *
     * @ORM\Column(name="dessert", type="string", length=255)
     */
    private $dessert;

    /**
     * @return mixed
     */
    public function getNomplat()
    {
        return $this->nomplat;
    }

    /**
     * @param mixed $nomplat
     */
    public function setNomplat($nomplat)
    {
        $this->nomplat = $nomplat;
    }

    /**

     * @ORM\ManyToOne(targetEntity="CantineBundle\Entity\plat")
     * @ORM\JoinColumn(name="nomplat",referencedColumnName="id")
     *
     */
    private $nomplat;

    /**
     * @var string
     *
     * @ORM\Column(name="supplement", type="string", length=255)
     */
    private $supplement;

    /**
     * @var int
     *
     * @ORM\Column(name="prix", type="integer")
     */
    private $prix;


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
     * Set boisson
     *
     * @param string $boisson
     *
     * @return menu
     */
    public function setBoisson($boisson)
    {
        $this->boisson = $boisson;

        return $this;
    }

    /**
     * Get boisson
     *
     * @return string
     */
    public function getBoisson()
    {
        return $this->boisson;
    }

    /**
     * Set dessert
     *
     * @param string $dessert
     *
     * @return menu
     */
    public function setDessert($dessert)
    {
        $this->dessert = $dessert;

        return $this;
    }

    /**
     * Get dessert
     *
     * @return string
     */
    public function getDessert()
    {
        return $this->dessert;
    }

    /**
     * Set supplement
     *
     * @param string $supplement
     *
     * @return menu
     */
    public function setSupplement($supplement)
    {
        $this->supplement = $supplement;

        return $this;
    }

    /**
     * Get supplement
     *
     * @return string
     */
    public function getSupplement()
    {
        return $this->supplement;
    }

    /**
     * Set prix
     *
     * @param integer $prix
     *
     * @return menu
     */
    public function setPrix($prix)
    {
        $this->prix = $prix;

        return $this;
    }

    /**
     * Get prix
     *
     * @return int
     */
    public function getPrix()
    {
        return $this->prix;
    }
}

